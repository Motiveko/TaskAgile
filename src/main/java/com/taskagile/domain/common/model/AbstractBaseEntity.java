package com.taskagile.domain.common.model;

import java.io.Serializable;

/**
 *  Serializable : 객체를 파일에 쓰거나 전송하기 위해서는 직렬화를 해야 하는데 그러기 위해 객체 클래스에 Serializable 인터페이스를 implements 하게 된다.
 *  이 때 The serializable class *** does not declare a static final serialVersionUID field of type long 라는 오류가 뜨는 걸 볼 수 있는데
 *  이를 해결하려면 serialVersionUID를 정의해줘야한다.
 * 	serialVersionUID는 직렬화에 사용되는 고유 ID인데 설정을 해주지 않아도 JVM에서 default로 생성해주나 자바에서 직접 선언하라고 권장한다. 이유는..
 *  JVM에 의한 디폴트 serialVersionUID 계산은 클래스의 세부 사항을 매우 민감하게 반영하기 때문에 컴파일러 구현체에 따라서 달라질 수 있어 deserialization 과정에서 예상하지 못한 InvalidClassException을 유발할 수 있다.
 *  
 *  참고 : https://hyeonstorage.tistory.com/253
 */
public abstract class AbstractBaseEntity implements Serializable{

	// 	
	private static final long serialVersionUID = -1153931912966528994L;
	
	public abstract boolean equals(Object obj);
	
	public abstract int hashcode();
	
	public abstract String toString();
}
