/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.lettuce.v5_1;

import io.lettuce.core.RedisClient;
import io.opentelemetry.instrumentation.lettuce.v5_1.AbstractLettuceAsyncClientTest;
import io.opentelemetry.instrumentation.testing.junit.AgentInstrumentationExtension;
import io.opentelemetry.instrumentation.testing.junit.InstrumentationExtension;
import org.junit.jupiter.api.extension.RegisterExtension;

class LettuceAsyncClientTest extends AbstractLettuceAsyncClientTest {
  @RegisterExtension
  static final InstrumentationExtension testing = AgentInstrumentationExtension.create();

  @Override
  public InstrumentationExtension testing() {
    return testing;
  }

  @Override
  protected RedisClient createClient(String uri) {
    return RedisClient.create(uri);
  }

  @Override
  protected boolean connectHasSpans() {
    return Boolean.getBoolean("testLatestDeps");
  }
}
