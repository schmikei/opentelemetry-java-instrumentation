/*
 * Copyright The OpenTelemetry Authors
 * SPDX-License-Identifier: Apache-2.0
 */

package io.opentelemetry.javaagent.instrumentation.opensearch.rest;

import static io.opentelemetry.instrumentation.api.incubator.semconv.db.internal.DbResponseStatusUtil.dbResponseStatusCode;

import io.opentelemetry.instrumentation.api.incubator.semconv.db.DbClientAttributesGetter;
import io.opentelemetry.semconv.incubating.DbIncubatingAttributes;
import javax.annotation.Nullable;
import org.opensearch.client.Response;

final class OpenSearchRestAttributesGetter
    implements DbClientAttributesGetter<OpenSearchRestRequest, Response> {

  @SuppressWarnings("deprecation") // using deprecated DbSystemIncubatingValues
  @Override
  public String getDbSystem(OpenSearchRestRequest request) {
    return DbIncubatingAttributes.DbSystemIncubatingValues.OPENSEARCH;
  }

  @Deprecated
  @Override
  @Nullable
  public String getUser(OpenSearchRestRequest request) {
    return null;
  }

  @Override
  @Nullable
  public String getDbNamespace(OpenSearchRestRequest request) {
    return null;
  }

  @Deprecated
  @Override
  @Nullable
  public String getConnectionString(OpenSearchRestRequest request) {
    return null;
  }

  @Override
  @Nullable
  public String getDbQueryText(OpenSearchRestRequest request) {
    return request.getMethod() + " " + request.getOperation();
  }

  @Override
  @Nullable
  public String getDbOperationName(OpenSearchRestRequest request) {
    return request.getMethod();
  }

  @Nullable
  @Override
  public String getResponseStatus(@Nullable Response response, @Nullable Throwable error) {
    return response != null ? dbResponseStatusCode(response.getStatusLine().getStatusCode()) : null;
  }
}
