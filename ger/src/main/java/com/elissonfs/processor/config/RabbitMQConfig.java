package com.elissonfs.processor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${queue.notification.name}")
  private String notificationQueue;

  @Value("${topic.exchange.notification.name}")
  private String topicExchangeNotificationName;

  @Value("${routing.notification.key}")
  private String rountingNotificationKey;

  @Value("${queue.processor.name}")
  private String processorQueue;

  @Value("${topic.exchange.processor.name}")
  private String topicExchangeProcessorName;

  @Value("${routing.processor.key}")
  private String rountingProcessorKey;

  @Bean
  public TopicExchange topicExchangeNotification() {
    return new TopicExchange(topicExchangeNotificationName);
  }

  @Bean
  public Queue queueNotification() {
    return new Queue(notificationQueue);
  }

  @Bean
  public Binding bindingNotification() {
    return BindingBuilder.bind(queueNotification()).to(topicExchangeNotification()).with(rountingNotificationKey);
  }

  @Bean
  public String topicExchangeNotificationName() {
    return topicExchangeNotificationName;
  }

  @Bean
  public TopicExchange topicExchangeProcessor() {
    return new TopicExchange(topicExchangeProcessorName);
  }

  @Bean
  public Queue queueProcessor() {
    return new Queue(processorQueue);
  }

  @Bean
  public Binding bindingProcessor() {
    return BindingBuilder.bind(queueProcessor()).to(topicExchangeProcessor()).with(rountingProcessorKey);
  }

  @Bean
  public SimpleRabbitListenerContainerFactory listenerContainerFactory(ConnectionFactory connectionFactory) {
    final var factory = new SimpleRabbitListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setMessageConverter(messageConverter());
    return factory;
  }

  @Bean
  public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
    final var rabbitTemplate = new RabbitTemplate(connectionFactory);
    rabbitTemplate.setMessageConverter(messageConverter());
    return rabbitTemplate;
  }

  @Bean
  public MessageConverter messageConverter() {
    return new Jackson2JsonMessageConverter();
  }
}
