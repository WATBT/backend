package alter.alter_core.service;

import alter.alter_core.domain.Member;
import alter.alter_core.repository.MemberRepository;
import alter.alter_core.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    회원가입
    public Long join(Member member) {

//        같은 이름이 있는 중복 회원 x
//        방법1
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        result.ifPresent(member1 -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

//        방법2
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(member1 -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

//    전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

//    ID로 조회
    public Optional<Member> findOne(Long memberID) {
        return memberRepository.findById(memberID);
    }
}
