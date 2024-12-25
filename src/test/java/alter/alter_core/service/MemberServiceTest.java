//package alter.alter_core.service;
//
//import alter.alter_core.domain.Member;
//import alter.alter_core.repository.MemoryMemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class MemberServiceTest {
//
//    MemberService memberService;
//    MemoryMemberRepository memoryMemberRepository;
//    @BeforeEach
//    public void beforeEach() {
//        memoryMemberRepository = new MemoryMemberRepository();
//        memberService = new MemberService(memoryMemberRepository);
//    }
//
//    @AfterEach
//    public void afterEach() {
//        memoryMemberRepository.clearStore();
//    }
//
//    @Test
//    void join() {
//        // given
//        Member member = new Member();
//        member.setName("spring");
//
//        // when
//        Long saveId = memberService.join(member);
//
//
//        //then
//        Member findMember = memberService.findOne(saveId).get();
//        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());;
//
//    }
//
//    @Test
//    public void 중복회원예외() {
//        Member member1 = new Member();
//        member1.setName("spring");
//
//        memberService.join(member1);
//
//        Member member2 = new Member();
//        member2.setName("spring");
//
//        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
////        try {
////            memberService.join(member2);
////            fail();
////        } catch (IllegalStateException e) {
////
////        }
//
//    }
//
//    @Test
//    void findMembers() {
//    }
//
//    @Test
//    void findOne() {
//    }
//}