package com.EZ.spring.member.store.Impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.EZ.spring.member.domain.MemberVO;
import com.EZ.spring.member.store.MemberStore;
@Repository
public class MemberStoreImpl implements MemberStore{
	
	@Override
	public int insertMember(SqlSession session, MemberVO member) {
	int result=	session.insert("MemberMapper.insertMember",member);
		return result;
	}

	@Override
	public int deleteMember(SqlSession session, String memberId) {
		int result = session.delete("MemberMapper.deletemember",memberId);
		return result;
	}

	@Override
	public int updatemember(SqlSession session, MemberVO member) {
		int result = session.update("MemberMapper.updatemember",member);
		return result;
	}

	@Override
	public MemberVO checkMemberLogin(SqlSession session, MemberVO member) {
		MemberVO result = session.selectOne("MemberMapper.checkMemberLogin",member);
		return result;
	}

	@Override
	public MemberVO selectOneById(SqlSession session, String memberId) {
		MemberVO result = session.selectOne("MemberMapper.selectOneById",memberId);
		return result;
	}

}
