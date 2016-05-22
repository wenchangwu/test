package com.oriental.finance.easymock;

import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.easymock.internal.RuntimeExceptionWrapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

/**
 * Created by wuwenchang on 22/5/16.
 */
@RunWith(EasyMockRunner.class)
public class MathApplicationTest {

    @TestSubject
    MathApplication mathApplication = new MathApplication();

    @Mock
    CalculatorService calculatorService;

    @Test
    public void testAdd() {
        EasyMock.expect(calculatorService.add(10.0, 20.0)).andReturn(30.0);
        //calculatorService.servieUsed();
        mathApplication.setCalculatorService(calculatorService);
        EasyMock.expectLastCall().times(1);
        //active the mock

        EasyMock.replay(calculatorService);

        Assert.assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);

        EasyMock.verify(calculatorService);
    }

    @Test(expected = RuntimeException.class)
       public void testMultiply() {
        EasyMock.expect(calculatorService.multiply(20.0, 10.0)).andThrow(new RuntimeException("multiply operation not implemented"));
        EasyMock.replay(calculatorService);

        Assert.assertEquals(mathApplication.multiply(20.0, 10.0), 10.0, 0);

        EasyMock.verify(calculatorService);
    }

    @Test
    public void testAddAndSubtract() {

        calculatorService = EasyMock.createStrictMock(CalculatorService.class);
        //add the behavior to add numbers
        EasyMock.expect(calculatorService.add(20.0, 10.0)).andReturn(30.0);

        //subtract the behavior to subtract numbers
        EasyMock.expect(calculatorService.subtract(20.0, 10.0)).andReturn(10.0);


        mathApplication.setCalculatorService(calculatorService);
        //activate the mock
        EasyMock.replay(calculatorService);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0), 30.0, 0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(20.0, 10.0), 10.0, 0);

        //verify call to calcService is made or not
        EasyMock.verify(calculatorService);
    }


    @Test
    public void testNiceMock(){

        calculatorService = EasyMock.createNiceMock(CalculatorService.class);
        mathApplication.setCalculatorService(calculatorService);

        //add the behavior to add numbers
        EasyMock.expect(calculatorService.add(20.0,10.0)).andReturn(30.0);

        //activate the mock
        EasyMock.replay(calculatorService);

        //test the add functionality
        Assert.assertEquals(mathApplication.add(20.0, 10.0),30.0,0);

        //test the subtract functionality
        Assert.assertEquals(mathApplication.subtract(0.0, 10.0),0.0,0);

        //test the multiply functionality
       // Assert.assertEquals(mathApplication.divide(0.0, 10.0),0.0,0);

        //test the divide functionality
        //Assert.assertEquals(mathApplication.multiply(0.0, 10.0),0.0,0);

        //verify call to calcService is made or not
        EasyMock.verify(calculatorService);
    }
}
