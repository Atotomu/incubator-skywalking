/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.apache.skywalking.oap.server.storage.plugin.influxdb.base;

import org.apache.skywalking.oap.server.core.analysis.config.NoneStream;
import org.apache.skywalking.oap.server.core.analysis.metrics.Metrics;
import org.apache.skywalking.oap.server.core.analysis.record.Record;
import org.apache.skywalking.oap.server.core.register.RegisterSource;
import org.apache.skywalking.oap.server.core.storage.IMetricsDAO;
import org.apache.skywalking.oap.server.core.storage.INoneStreamDAO;
import org.apache.skywalking.oap.server.core.storage.IRecordDAO;
import org.apache.skywalking.oap.server.core.storage.IRegisterDAO;
import org.apache.skywalking.oap.server.core.storage.StorageBuilder;
import org.apache.skywalking.oap.server.core.storage.StorageDAO;
import org.apache.skywalking.oap.server.library.client.jdbc.hikaricp.JDBCHikariCPClient;
import org.apache.skywalking.oap.server.storage.plugin.influxdb.InfluxClient;
import org.apache.skywalking.oap.server.storage.plugin.jdbc.h2.dao.H2RegisterDAO;

public class InfluxStorageDAO implements StorageDAO {
    private final InfluxClient influxClient;
    private final JDBCHikariCPClient client;

    public InfluxStorageDAO(JDBCHikariCPClient client, InfluxClient influxdbClient) {
        this.client = client;
        this.influxClient = influxdbClient;
    }

    @Override
    public IMetricsDAO newMetricsDao(StorageBuilder<Metrics> storageBuilder) {
        return new MetricsDAO(influxClient, storageBuilder);
    }

    @Override
    public IRegisterDAO newRegisterDao(StorageBuilder<RegisterSource> storageBuilder) {
        return new H2RegisterDAO(client, storageBuilder);
    }

    @Override
    public IRecordDAO newRecordDao(StorageBuilder<Record> storageBuilder) {
        return new RecordDAO(influxClient, storageBuilder);
    }

    @Override
    public INoneStreamDAO newNoneStreamDao(StorageBuilder<NoneStream> storageBuilder) {
        return new NoneStreamDAO(influxClient, storageBuilder);
    }
}
