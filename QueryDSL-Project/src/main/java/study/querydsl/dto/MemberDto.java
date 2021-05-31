package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberDto {

    private String username;
    private int age;

    public MemberDto() {
    }

    /**
     * @QueryProjection 을 넣고 compileQuerydsl 을 돌리면 Q 타입이 생성된다.
     * @QueryProjection 을 사용하면 단점이, dto 를 깔끔하게 가져가고 싶은경우 쓰기 애매하다.
     * 왜냐하면 Dto 가 QueryDsl 에 의존적이게 되기 때문이다.
     * @param username
     * @param age
     */
    @QueryProjection
    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }

}
