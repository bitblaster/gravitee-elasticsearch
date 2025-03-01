/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.reporter.elasticsearch.spring.context;

import io.gravitee.reporter.elasticsearch.config.ReporterConfiguration;
import io.gravitee.reporter.elasticsearch.indexer.AbstractIndexer;
import io.gravitee.reporter.elasticsearch.indexer.name.AbstractIndexNameGenerator;
import io.gravitee.reporter.elasticsearch.mapping.AbstractIndexPreparer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author GraviteeSource Team
 */
public abstract class AbstractElasticBeanRegistrer {

    protected abstract Class<? extends AbstractIndexer> getIndexerClass();
    protected abstract Class<? extends AbstractIndexPreparer> getIndexPreparerClass(ReporterConfiguration configuration);
    protected abstract Class<? extends AbstractIndexNameGenerator> getIndexNameGeneratorClass(ReporterConfiguration configuration);

    public void register(DefaultListableBeanFactory beanFactory, ReporterConfiguration configuration) {
        beanFactory.registerBeanDefinition("indexer", BeanDefinitionBuilder.rootBeanDefinition(getIndexerClass()).getBeanDefinition());
        beanFactory.registerBeanDefinition("indexPreparer", BeanDefinitionBuilder.rootBeanDefinition(getIndexPreparerClass(configuration)).getBeanDefinition());
        beanFactory.registerBeanDefinition("indexNameGenerator", BeanDefinitionBuilder.rootBeanDefinition(getIndexNameGeneratorClass(configuration)).getBeanDefinition());
    }
}
