package com.gexingw;


import com.gexingw.diffutil.DiffUtil;
import com.gexingw.diffutil.Order;
import com.gexingw.diffutil.OrderItem;
import com.gexingw.diffutil.OrderShippingAddress;
import org.javers.core.Changes;
import org.javers.core.ChangesByObject;
import org.javers.core.Javers;
import org.javers.core.JaversBuilder;
import org.javers.core.diff.Change;
import org.javers.core.diff.Diff;
import org.javers.core.diff.ListCompareAlgorithm;
import org.javers.core.diff.changetype.*;
import org.javers.core.metamodel.object.GlobalId;
import org.javers.repository.jql.JqlQuery;
import org.javers.repository.jql.QueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Unit test for simple Application.
 */

@RunWith(PowerMockRunner.class)
public class AppTest {

    @Test
    public void objectDiff() {
        Order order1 = new Order().setId(1L);
        Order order2 = new Order().setId(2L);

        Javers javers = JaversBuilder.javers().build();
        Diff compare = javers.compare(order1, order2);
    }

    @Test
    public void TestDiff1() {
        String orderNo = "ORD1234";
        long orderId = 1L;
        OrderItem orderItem10 = new OrderItem().setId(10L).setOrderId(orderId).setOrderNo(orderNo).setProductId(100L)
                .setProductName("Product-100").setProductPrice(new BigDecimal("100.00"))
                .setProductNum(1);
        OrderItem orderItem11 = new OrderItem().setId(11L).setOrderId(orderId).setOrderNo(orderNo).setProductId(101L)
                .setProductName("Product-101").setProductPrice(new BigDecimal("101.00"))
                .setProductNum(1);
        OrderShippingAddress shippingAddress1000 = new OrderShippingAddress().setId(1000L).setOrderId(orderId).setOrderNo(orderNo).setUserName("User-1")
                .setUserPhone("15212341234").setDeliveryTime(LocalDateTime.now());
        Order order1 = new Order(orderId, orderNo, Arrays.asList(orderItem10, orderItem11), shippingAddress1000);

        OrderItem orderItem12 = new OrderItem().setId(12L).setOrderId(orderId).setOrderNo(orderNo).setProductId(101L)
                .setProductName("Product-101").setProductPrice(new BigDecimal("101.00"))
                .setProductNum(1);
        OrderShippingAddress shippingAddress1001 = new OrderShippingAddress().setId(1001L).setOrderId(orderId).setOrderNo(orderNo).setUserName("User-1")
                .setUserPhone("15212345678").setDeliveryTime(LocalDateTime.of(2024, 12, 12, 12, 12, 12));
        Order order2 = new Order(2L, orderNo, Arrays.asList(orderItem10, orderItem12), shippingAddress1001);

        Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE).build();
        Diff diff = javers.compare(order1, order2);

        System.out.println(diff.prettyPrint());
    }

    @Test
    public void TestGroupByObject() {
        String orderNo = "ORD1234";
        long orderId = 1L;
        OrderItem orderItem10 = new OrderItem().setId(10L).setOrderId(orderId).setOrderNo(orderNo).setProductId(100L)
                .setProductName("Product-100").setProductPrice(new BigDecimal("100.00"))
                .setProductNum(1);
        OrderItem orderItem11 = new OrderItem().setId(11L).setOrderId(orderId).setOrderNo(orderNo).setProductId(101L)
                .setProductName("Product-101").setProductPrice(new BigDecimal("101.00"))
                .setProductNum(1);
        OrderShippingAddress shippingAddress1 = new OrderShippingAddress().setId(1000L).setOrderId(orderId).setOrderNo(orderNo).setUserName("User-1")
                .setUserPhone("15212341234").setDeliveryTime(LocalDateTime.now());
        Order order1 = new Order(orderId, orderNo, Arrays.asList(orderItem10, orderItem11), shippingAddress1);

        OrderItem orderItem12 = new OrderItem().setId(12L).setOrderId(orderId).setOrderNo(orderNo).setProductId(101L)
                .setProductName("Product-101").setProductPrice(new BigDecimal("101.00"))
                .setProductNum(1);
        OrderShippingAddress shippingAddress2 = new OrderShippingAddress().setId(1000L).setOrderId(orderId).setOrderNo(orderNo).setUserName("User-1")
                .setUserPhone("15212345678").setDeliveryTime(LocalDateTime.of(2024, 12, 12, 12, 12, 12));
        Order order2 = new Order(2L, orderNo, Arrays.asList(orderItem10, orderItem12), shippingAddress2);

        Javers javers = JaversBuilder.javers().withListCompareAlgorithm(ListCompareAlgorithm.LEVENSHTEIN_DISTANCE).build();
        List<ChangesByObject> changeObjects = javers.compare(order1, order2).groupByObject();
//        System.out.println(changeObjects);

        Map<String, List<DiffUtil.DiffItem>> changeMap = new HashMap<>(changeObjects.size());
        changeObjects.forEach(changeObject -> {
            System.out.println(".");
            System.out.println(changeObject);
            List<NewObject> newObjects = changeObject.getNewObjects();
            System.out.println("newObjects: " + newObjects);
            List<ObjectRemoved> objectsRemoved = changeObject.getObjectsRemoved();
            System.out.println("objectsRemoved: " + objectsRemoved);
            List<PropertyChange> propertyChanges = changeObject.getPropertyChanges();
            System.out.println("propertyChanges: " + propertyChanges);

            List<DiffUtil.DiffItem> diffItems = changeMap.getOrDefault(changeObject.getGlobalId().getTypeName(), new ArrayList<>());
            for (NewObject newObject : newObjects) {
                diffItems.add(new DiffUtil.DiffItem(DiffUtil.ADD, null, newObject.getAffectedObject()));
            }

            for (ObjectRemoved objectRemoved : objectsRemoved) {
                diffItems.add(new DiffUtil.DiffItem(DiffUtil.DELETE, objectRemoved.getAffectedObject(), null));
            }

            for (PropertyChange propertyChange : propertyChanges) {
                diffItems.add(new DiffUtil.DiffItem(DiffUtil.UPDATE, propertyChange.getAffectedObject(), propertyChange.getRight()));
            }
            changeMap.put(changeObject.getGlobalId().getTypeName(), diffItems);

            System.out.println("------------------------");
            System.out.println();
        });

        System.out.println(changeMap);

    }

    @Test
    public void testCommit() {
        String orderNo = "ORD1234";
        long orderId = 1L;
        OrderItem orderItem10 = new OrderItem().setId(10L).setOrderId(orderId).setOrderNo(orderNo).setProductId(100L)
                .setProductName("Product-100").setProductPrice(new BigDecimal("100.00"))
                .setProductNum(1);
        OrderItem orderItem11 = new OrderItem().setId(11L).setOrderId(orderId).setOrderNo(orderNo).setProductId(101L)
                .setProductName("Product-101").setProductPrice(new BigDecimal("101.00"))
                .setProductNum(1);
        OrderShippingAddress shippingAddress1000 = new OrderShippingAddress().setId(1000L).setOrderId(orderId).setOrderNo(orderNo).setUserName("User-1")
                .setUserPhone("15212341234").setDeliveryTime(LocalDateTime.now());
        Order order1 = new Order(orderId, orderNo, Arrays.asList(orderItem10, orderItem11), shippingAddress1000);

        OrderItem orderItem12 = new OrderItem().setId(12L).setOrderId(orderId).setOrderNo(orderNo).setProductId(101L)
                .setProductName("Product-101").setProductPrice(new BigDecimal("101.00"))
                .setProductNum(1);
        OrderShippingAddress shippingAddress1001 = new OrderShippingAddress().setId(1001L).setOrderId(orderId).setOrderNo(orderNo).setUserName("User-1")
                .setUserPhone("15212345678").setDeliveryTime(LocalDateTime.of(2024, 12, 12, 12, 12, 12));

        Order newOrder1 = new Order(orderId, orderNo, Arrays.asList(orderItem10, orderItem11), shippingAddress1000);
        Order oldOrder1 = new Order(orderId, orderNo, Arrays.asList(orderItem10, orderItem12), shippingAddress1001);

        Javers javers = JaversBuilder.javers().build();
        javers.commit(Order.class.getName(), newOrder1);
        javers.commit(Order.class.getName(), oldOrder1);

        Changes byTypeChanges = javers.findChanges(QueryBuilder.byClass(OrderItem.class).build());
        System.out.println(byTypeChanges.prettyPrint());

        System.out.println();
        System.out.println();

        JqlQuery query = QueryBuilder.byClass(OrderItem.class).build();
        Changes changes = javers.findChanges(query);
        System.out.println(changes.prettyPrint());
    }

    @Test
    public void testCommitDiff() {
        String orderNo = "ORD1234";
        long orderId = 1L;
        OrderItem orderItem10 = new OrderItem().setId(10L).setOrderId(orderId).setOrderNo(orderNo).setProductId(100L)
                .setProductName("Product-100").setProductPrice(new BigDecimal("100.00"))
                .setProductNum(1);
        OrderItem orderItem11 = new OrderItem().setId(11L).setOrderId(orderId).setOrderNo(orderNo).setProductId(101L)
                .setProductName("Product-101").setProductPrice(new BigDecimal("101.00"))
                .setProductNum(1);
        OrderShippingAddress shippingAddress1000 = new OrderShippingAddress().setId(1000L).setOrderId(orderId).setOrderNo(orderNo).setUserName("User-1")
                .setUserPhone("15212341234").setDeliveryTime(LocalDateTime.now());
        Order snapshot = new Order(orderId, orderNo, Arrays.asList(orderItem10, orderItem11), shippingAddress1000);


        Javers javers = JaversBuilder.javers().build();
        javers.commit(Order.class.getName(), snapshot);

//        javers.commit()


    }


}
