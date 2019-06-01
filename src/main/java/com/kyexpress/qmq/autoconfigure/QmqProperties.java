package com.kyexpress.qmq.autoconfigure;

import com.kyexpress.qmq.constant.QmqConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author kye
 */
@ConfigurationProperties("spring.qmq")
public class QmqProperties {
	/**
	 * QMQ AppCode，默认值 default_app_code
	 */
	private String appCode = QmqConstant.DEFAULT_APP_CODE;

	/**
	 * QMQ MetaServer Address，默认值 http://127.0.0.1:8080/meta/address，
	 * 可使用 host 和 port 配置替换
	 */
	private String metaServer = QmqConstant.DEFAULT_META_SERVER;

	/**
	 * QMQ MetaServer host，可使用 metaServer 配置替换，优先使用 host:port
	 */
	private String host;

	/**
	 * QMQ MetaServer port，可使用 metaServer 配置替换，优先使用 host:port
	 */
	private Integer port;

	/**
	 * QMQ 消息发送者配置
	 */
	private final Producer producer = new Producer();

	/**
	 * QMQ 消息发送模板配置
	 * @see com.kyexpress.qmq.QmqTemplate
	 */
	private final Template template = new Template();

	/**
	 * QMQ 消息接收者配置
	 */
	private final Consumer consumer = new Consumer();

	public String getAppCode() {
		return StringUtils.defaultString(appCode, QmqConstant.DEFAULT_APP_CODE);
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getMetaServer() {
		return StringUtils.defaultString(metaServer, QmqConstant.DEFAULT_META_SERVER);
	}

	public void setMetaServer(String metaServer) {
		this.metaServer = metaServer;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Producer getProducer() {
		return producer;
	}

	public Template getTemplate() {
		return template;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	/**
	 * QMQ 消息发送者配置
	 */
	public static class Producer {
		/**
		 * 异步发送队列大小，默认 10000
		 */
		private Integer maxQueueSize = QmqConstant.DEFAULT_MAX_QUEUE_SIZE;

		/**
		 * 发送线程数，默认 3
		 */
		private Integer sendThreads = QmqConstant.DEFAULT_SEND_THREADS;

		/**
		 * 默认每次发送时最大批量大小，默认 30
		 */
		private Integer sendBatch = QmqConstant.DEFAULT_SEND_BATCH;

		/**
		 * 如果消息发送失败，重试次数，默认 10
		 */
		private Integer sendTryCount = QmqConstant.DEFAULT_SEND_TRY_COUNT;

		/**
		 * 发送消息超时时间，单位：毫秒，默认 5 秒超时；源代码中没有 set 方法，未生效
		 */
		private Long sendTimeoutMillis = QmqConstant.DEFAULT_SEND_TIMEOUT_MILLIS;

		/**
		 * 是否同步发送，默认使用异步发送；源代码中没有 set 方法，未生效
		 */
		private boolean syncSend = false;

		public Integer getMaxQueueSize() {
			return maxQueueSize != null && maxQueueSize > 0 ? maxQueueSize : QmqConstant.DEFAULT_MAX_QUEUE_SIZE;
		}

		public void setMaxQueueSize(Integer maxQueueSize) {
			this.maxQueueSize = maxQueueSize;
		}

		public Integer getSendThreads() {
			return sendThreads != null && sendThreads > 0 ? sendThreads : QmqConstant.DEFAULT_SEND_THREADS;
		}

		public void setSendThreads(Integer sendThreads) {
			this.sendThreads = sendThreads;
		}

		public Integer getSendBatch() {
			return sendBatch != null && sendBatch > 0 ? sendBatch : QmqConstant.DEFAULT_SEND_BATCH;
		}

		public void setSendBatch(Integer sendBatch) {
			this.sendBatch = sendBatch;
		}

		public Integer getSendTryCount() {
			return sendTryCount != null && sendTryCount > 0 ? sendTryCount : QmqConstant.DEFAULT_SEND_TRY_COUNT;
		}

		public void setSendTryCount(Integer sendTryCount) {
			this.sendTryCount = sendTryCount;
		}

		public Long getSendTimeoutMillis() {
			return sendTimeoutMillis != null && sendTimeoutMillis > 0 ?
					sendTimeoutMillis :
					QmqConstant.DEFAULT_SEND_TIMEOUT_MILLIS;
		}

		@Deprecated
		public void setSendTimeoutMillis(Long sendTimeoutMillis) {
			this.sendTimeoutMillis = sendTimeoutMillis;
		}

		public boolean isSyncSend() {
			return syncSend;
		}

		@Deprecated
		public void setSyncSend(boolean syncSend) {
			this.syncSend = syncSend;
		}
	}

	/**
	 * QMQ 消息发送模板配置
	 * @see com.kyexpress.qmq.QmqTemplate
	 */
	public static class Template {
		/**
		 * 默认消息发送主题，默认值 default_subject
		 */
		private String defaultSubject = QmqConstant.DEFAULT_SUBJECT;

		public String getDefaultSubject() {
			return StringUtils.defaultString(defaultSubject, QmqConstant.DEFAULT_SUBJECT);
		}

		public void setDefaultSubject(String defaultSubject) {
			this.defaultSubject = defaultSubject;
		}
	}

	/**
	 * QMQ 消息接收者配置
	 */
	public static class Consumer {
		/**
		 * 线程池大小，默认 2
		 */
		private Integer corePoolSize = QmqConstant.DEFAULT_CORE_POOL_SIZE;

		/**
		 * 最大线程池大小，默认 2
		 */
		private Integer maxPoolSize = QmqConstant.DEFAULT_MAX_POOL_SIZE;

		/**
		 * 线程池队列大小，默认 1000
		 */
		private Integer queueCapacity = QmqConstant.DEFAULT_QUEUE_CAPACITY;

		/**
		 * 线程池名称前缀，默认 qmq-process
		 */
		private String threadNamePrefix = QmqConstant.DEFAULT_THREAD_NAME_PREFIX;

		public Integer getCorePoolSize() {
			return corePoolSize != null && corePoolSize > 0 ? corePoolSize : QmqConstant.DEFAULT_CORE_POOL_SIZE;
		}

		public void setCorePoolSize(Integer corePoolSize) {
			this.corePoolSize = corePoolSize;
		}

		public Integer getMaxPoolSize() {
			return maxPoolSize != null && maxPoolSize > 0 ? maxPoolSize : QmqConstant.DEFAULT_MAX_POOL_SIZE;
		}

		public void setMaxPoolSize(Integer maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		public Integer getQueueCapacity() {
			return queueCapacity != null && queueCapacity > 0 ? queueCapacity : QmqConstant.DEFAULT_QUEUE_CAPACITY;
		}

		public void setQueueCapacity(Integer queueCapacity) {
			this.queueCapacity = queueCapacity;
		}

		public String getThreadNamePrefix() {
			return StringUtils.defaultString(threadNamePrefix, QmqConstant.DEFAULT_THREAD_NAME_PREFIX);
		}

		public void setThreadNamePrefix(String threadNamePrefix) {
			this.threadNamePrefix = threadNamePrefix;
		}
	}
}