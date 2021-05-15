package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class QuerydslApplicationTests {

    /**
     * @PersistenceContext : 자바 표준 스펙에서는 이렇게 써야함
     * @Autowired : 스프링에서는 이렇게 써도됨
     * 만약, 프레임워크가 다른거로 바뀔거같으면 @PersistenceContext 를 써야한다.
     * @PersistenceContext 로 사용하는 것을 추천. 프레임워크에 종속적이지 않게 개발하는것이 중요하다.
     */
    @PersistenceContext
    EntityManager entityManager;

    /**
     *  참고: 스프링 부트에 아무런 설정도 하지 않으면 h2 DB를 메모리 모드로 JVM 안에서 실행한다
     */
    @Test
    void contextLoads() {
        Hello hello = new Hello();
        entityManager.persist(hello);

        JPAQueryFactory query = new JPAQueryFactory(entityManager);
        // QHello qHello = new QHello("h");
        QHello qHello = QHello.hello; // QueryDSL Q타입 동작 확인
        
        Hello result = query
                        .selectFrom(qHello)
                        .fetchOne();

        assertThat(result).isEqualTo(hello);
        // lombok 동작 확인 hello.getId();
        assertThat(result.getId()).isEqualTo(hello.getId());
    }

}
