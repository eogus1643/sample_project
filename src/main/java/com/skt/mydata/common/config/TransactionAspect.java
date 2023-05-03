package com.skt.mydata.common.config;

import java.util.Collections;
import java.util.HashMap;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect
@Configuration
public class TransactionAspect {

	private PlatformTransactionManager transactionManager;

	@Bean
	public TransactionInterceptor transactionAdvice() {
		TransactionInterceptor txAdvice = new TransactionInterceptor();
		NameMatchTransactionAttributeSource txAttributeSource = new NameMatchTransactionAttributeSource();
		RuleBasedTransactionAttribute txAttribute = new RuleBasedTransactionAttribute();

		txAttribute.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		txAttribute.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

		HashMap<String, TransactionAttribute> txMethods = new HashMap<>();
		txMethods.put("*", txAttribute);
		txAttributeSource.setNameMap(txMethods);

		txAdvice.setTransactionAttributeSource(txAttributeSource);
		txAdvice.setTransactionManager(transactionManager);

		return txAdvice;
	}

	@Bean
	public Advisor transactionAdviceAdvisor() {
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("execution(* com.skt.mydata..service.impl.*Impl.*(..))");

		return new DefaultPointcutAdvisor(pointcut, transactionAdvice());
	}
}

