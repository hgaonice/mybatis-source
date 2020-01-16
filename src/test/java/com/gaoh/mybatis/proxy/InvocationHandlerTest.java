package com.gaoh.mybatis.proxy;

import java.lang.reflect.Proxy;

/**
 * @author gaoh
 * @version 1.0
 * @date 2020/1/16 17:28
 */
public class InvocationHandlerTest {

  public static void main(String[] args) {
    IVehical iVehical = new IVehicalImpl();

    IVehical vehical = (IVehical) Proxy.newProxyInstance(iVehical.getClass().getClassLoader(),
      IVehicalImpl.class.getInterfaces(),
      new VehicalInvacationHandler(iVehical));
    vehical.run();
  }
}
