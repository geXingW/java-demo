package com.gexingw.unittest.service;

import com.gexingw.unittest.entity.Order;
import com.gexingw.unittest.entity.Product;
import com.gexingw.unittest.exception.InvalidParamException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

//@SpringBootTest(classes = UnitTestApplication.class)
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    // 模拟掉ProductService所有方法，仅测试OrderService
    @Mock
    private ProductService productService;

    @Spy
    @InjectMocks
    private OrderService orderService;

    private OrderServiceTest() {
    }

    public static OrderServiceTest createOrderServiceTest() {
        return new OrderServiceTest();
    }

    @Test
    public void testNew() {
        OrderService orderService = new OrderService();
        Assertions.assertEquals(1, orderService.getById(1L).getId());
    }

    @Test
    public void testAutowired() {
        Assertions.assertEquals(1, orderService.getById(1L).getId());
    }

    @Test
    public void testMock() {
        OrderService orderService = Mockito.mock(OrderService.class);
        Mockito.when(orderService.getById(1L)).thenReturn(new Order().setId(1L));
        Order order = orderService.getById(1L);
        System.out.println(order);
    }

    @Test
    public void testSpy() {
        OrderService orderService = Mockito.spy(OrderService.class);
        Order order = orderService.getById(1L);
        System.out.println(order);
    }

    @Test
    public void testMapper() {

//        WhiteBox.invoke(orderService, "setProductService", productService);
//        Mockito.doReturn("Changed order User: ").when(orderService).

//        Mockito.doReturn(new Product().setId(2L)).when(productService).getById(1L);
//        System.out.println(orderService.getById(1L));
//        System.out.println(orderService.getById(1L));

//        Mockito.verify(productService).getById(1L);
//        Mockito.verify(productService).getById(1L);
//        Mockito.verify(productService, Mockito.never()).delete(1L);
//        Mockito.verify(productService, Mockito.times(1)).getById(1L);

//        Mockito.doCallRealMethod().when(productService).getById(1L);
//        System.out.println(orderService.getById(1L));

//        System.out.println(ProductService.staticMethod());
//
//        try(MockedStatic<ProductService> mockedStatic = Mockito.mockStatic(ProductService.class)){
//            mockedStatic.when(ProductService::staticMethod).thenReturn("Mock static method called.");
//            System.out.println(ProductService.staticMethod());
//        }

//        Mockito.doReturn(new Product().setId(1L), new Product().setId(2L), new Product().setId(3L)).when(productService).getById(1L);
//        System.out.println(orderService.getById(1L));
//        System.out.println(orderService.getById(1L));
//        System.out.println(orderService.getById(1L));
//        System.out.println(orderService.getById(1L));
//        System.out.println(orderService.getById(1L));

//        Mockito.doThrow(new InvalidParamException("Invalid Param")).when(productService).getById(1L);
//        System.out.println(orderService.getById(1L));

//        Mockito.doAnswer(invocation -> {
//            Long productId = invocation.getArgument(0);
//            return new Product().setId(productId);
//        }).when(productService).getById(3L);
//        System.out.println(orderService.getById(3L));

//        OrderService orderService = new OrderService();
//        orderService.setProductService(new ProductService());

//        System.out.println(this.productService);
//        Order order = orderService.getById(1L);
//        System.out.println(order);

//        Mockito.when(productService.getById(1L)).thenReturn(new Product().setId(2L));
//        System.out.println(productService.getById(1L));
    }

    @Test
    public void testMockReturn() {
        // getById本应返回id=1的Product，此处模拟返回id=2的Product
        Mockito.doReturn(new Product().setId(2L)).when(productService).getById(1L);
        Assertions.assertEquals(2L, productService.getById(1L).getId(), "应该返回Stub的值为2L！");

        // 验证productService.getById(1L)被调用过
        Mockito.verify(productService).getById(Mockito.anyLong());
        // 验证productService.delete(1L)未被调用过
        Mockito.verify(productService, Mockito.never()).delete(1L);
        // 验证productService.getById(1L)被调用过1次
        Mockito.verify(productService, Mockito.times(1)).getById(1L);
    }

    @Test
    public void testMockStatusMethod() {
        // 创建模拟对象
        try (MockedStatic<ProductService> mockedStatic = Mockito.mockStatic(ProductService.class)) {
            // 利用Stub进行返回值模拟
            mockedStatic.when(ProductService::staticMethod).thenReturn("Mock static method called.");
            // 调用静态方法
            String staticMethodReturn = ProductService.staticMethod();

            // 断言
            // 应该返回模拟结果
            Assertions.assertEquals("Mock static method called.", staticMethodReturn, "应当返回模拟结果！");
            // 不应该返回真实结果
            Assertions.assertNotEquals("Static method called.", staticMethodReturn, "不应该返回实际结果！");
        }
    }

    @Test
    public void testCallRealMethod() {
        // 模拟返回值
        Mockito.doReturn(new Product().setId(2L)).when(productService).getById(1L);
        // 断言
        // Mock生效。返回Mock值
        Assertions.assertEquals(2L, productService.getById(1L).getId(), "调用模拟方法返回2L");
        // 验证方法被调用过至少一次
        Mockito.verify(productService, Mockito.atLeastOnce()).getById(1L);

        // 调用实际方法
        Mockito.doCallRealMethod().when(productService).getById(1L);
        // 断言
        // 调用真实方法，返回实际值
        Assertions.assertEquals(1L, productService.getById(1L).getId(), "调用真实方法返回1L");
        // 验证方法调用次数增加一次，为2次
        Mockito.verify(productService, Mockito.times(2)).getById(1L);

    }

    @Test
    public void testMockLoopReturn() {
        // 设置3个模拟返回值
        Mockito.doReturn(new Product().setId(1L), new Product().setId(2L), new Product().setId(3L)).when(productService).getById(1L);
        Assertions.assertEquals(1L, productService.getById(1L).getId(), "第一次调用返回1L");
        Assertions.assertEquals(2L, productService.getById(1L).getId(), "第二次调用返回2L");
        Assertions.assertEquals(3L, productService.getById(1L).getId(), "第三次调用返回2L");
        Assertions.assertEquals(3L, productService.getById(1L).getId(), "第四次调用返回2L");
        Assertions.assertEquals(3L, productService.getById(1L).getId(), "第五次调用返回2L");
    }

    @Test
    public void testMockThrow() {
        // 调用getById方法时抛出异常
        Mockito.doThrow(new InvalidParamException("Invalid Param")).when(productService).getById(1L);
        // 断言，方法调用抛出InvalidParamException异常
        InvalidParamException exception = Assertions.assertThrows(InvalidParamException.class, () -> productService.getById(1L), "此处应抛出异常！");
        // 此处可以拿到异常，对异常信息再次校验
        Assertions.assertEquals("Invalid Param", exception.getMessage(), "异常信息应为Invalid Param");
    }

    @Test
    public void testMockAnswer() {
        Mockito.doAnswer(invocation -> {
            // 获取传参
            Long productId = invocation.getArgument(0);
            // 构造一个自定义的Product对象
            return new Product().setId(productId);
        }).when(productService).getById(Mockito.anyLong());

        // 断言
        Assertions.assertEquals(3L, productService.getById(3L).getId(), "传3L应返回3L");
        Assertions.assertEquals(2L, productService.getById(2L).getId(), "传2L应返回2L");
        Assertions.assertEquals(1L, productService.getById(1L).getId(), "传1L应返回1L");
        // 验证方法被调用了三次
        Mockito.verify(productService, Mockito.times(3)).getById(Mockito.anyLong());
    }

    /**
     * 验证方法调用次数
     */
    @Test
    public void testVerify() {
        // 模拟方法返回值
        Mockito.doReturn(new Product().setId(2L)).when(productService).getById(1L);
        // 调用productService的getById方法，未调用orderService的getById方法
        productService.getById(1L);
        // 验证productService的getById方法被调用过
        Mockito.verify(productService).getById(Mockito.anyLong());
        // 验证orderService的getById方法未被调用过
        Mockito.verify(orderService, Mockito.never()).getById(1L);

        // 验证方法至少被调用一次
        Mockito.verify(productService, Mockito.atLeastOnce()).getById(Mockito.anyLong());
        // 验证方法被调用一次
        Mockito.verify(productService, Mockito.atLeastOnce()).getById(Mockito.anyLong());

        // 触发第二次调用
        productService.getById(1L);
        // 验证方法被调用两次
        Mockito.verify(productService, Mockito.times(2)).getById(Mockito.anyLong());
    }

}
