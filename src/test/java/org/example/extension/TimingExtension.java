package org.example.extension;

import java.lang.reflect.Method;
import java.util.logging.Logger;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class TimingExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

  private static final Logger LOGGER = Logger.getLogger(TimingExtension.class.getName());

  @Override
  public void beforeTestExecution(ExtensionContext context) throws Exception {
    getStore(context).put(context.getRequiredTestMethod(), System.currentTimeMillis());
  }

  @Override
  public void afterTestExecution(ExtensionContext context) throws Exception {
    Method testMethod = context.getRequiredTestMethod();
    long start = getStore(context).remove(testMethod, long.class);
    long duration = System.currentTimeMillis() - start;

    LOGGER.info(String.format("Test method [%s] tok %s ms.", testMethod.getName(), duration));
  }

  private ExtensionContext.Store getStore(ExtensionContext context) {
    return context.getStore(ExtensionContext.Namespace.create(getClass(), context));
  }

}
