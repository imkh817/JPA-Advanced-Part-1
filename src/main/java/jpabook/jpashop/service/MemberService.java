package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public Long join(Member member) {
        // 시간 시작하는 코드

        validateDuplicationMember(member); // 중복 회원 검증
        memberRepository.save(member);

        // 시간 끝내는 메서드
        // 끝내는메서드 - 시작하는 = 걸린시간
        return member.getId();
    }

    private void validateDuplicationMember(Member member) {
        // Exception
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return Optional.ofNullable(memberRepository.findOne(memberId))
                .orElseThrow(() -> new IllegalStateException("Member not found with id : " + memberId));
    }
}
