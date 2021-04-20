///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.muni.fi.pa165.interceptor;
//
//import java.util.Calendar;
//import javax.interceptor.AroundInvoke;
//import javax.interceptor.InvocationContext;
//
//
//
///**
// *
// * @author Aubrey Oosthuizen
// * 
// * Interceptor class used to authorize user before executing bean or bean methods. This class is still only
// * used as conceptual test and should be disregarded in general context at this point in the project.
// * 
// * For all EJB's to call a single interceptor it should be specified in the ejb deployment jar or "ejb-jar.xml"
// * with the following XML
// * <interceptor-binding>
// * <ejb-name>*</ejb-name>
// * <interceptor-class>com.muni.fi.pa165.interceptor.AuthorizationInterceptor</interceptor-class>
// * </interceptor-binding>
// */
//public class AuthorizationInterceptor {
//    
//@AroundInvoke
//public Object interceptorMethod(InvocationContext inContext) throws Exception{
//   System.out.println("Method : " + inContext.getMethod().getName() + " invoked at " + Calendar.getInstance().getTimeInMillis());
//   
//   try {
//       return inContext.proceed();
//   } finally {
//       System.out.println("Method : " + inContext.getMethod().getName() + " exited at " + Calendar.getInstance().getTimeInMillis());
//   }
//}
//
//}
