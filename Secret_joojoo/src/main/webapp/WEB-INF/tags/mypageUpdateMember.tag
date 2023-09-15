<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="starlight" %>

<c:if test="${mdata.signUpKind eq '구글'}">
    <!-- Account -->
    <div class="card-body">
    <form id="updateMemberForm_Google" action="updateMember.do" method="POST">

        <div class="row">

            <div class="mb-3 col-md-6">
                <label for="googlemID" class="form-label">이메일</label>
                <input
                        class="form-control"
                        type="text"
                        id="googlemID"
                        name="mID"
                        value="${mdata.mID}"
                        placeholder="이메일을 입력 하세요."
                        required="required"
                        readonly="readonly"
                />
            </div>

            <div class="mb-3 col-md-6">
                <label for="googlemPW" class="form-label">비밀번호</label>
                <input
                        class="form-control"
                        type="text"
                        id="googlemPW"
                        name="tmpMpw"
                        placeholder="구글 비밀번호는 구글에 문의해 주세요."
                        required="required"
                        readonly="readonly"
                />
            </div>

            <div class="mb-3 col-md-6">
                <label for="googlemName" class="form-label">이름 (닉네임)</label>
                <input
                        class="form-control"
                        type="text"
                        id="googlemName"
                        name="tmpMname"
                        value="${mdata.mName}"
                        placeholder="이름 (닉네임) 을 입력 하세요."
                        required="required"
                        readonly="readonly"
                />
            </div>

            <div class="mb-3 col-md-6">
                <label
                        class="subscription"
                        for="googlesubscription">
                    구독 여부 [ 0 : 구독 X | 1 : 구독 O ]
                </label>
                <input
                        class="form-control"
                        type="number"
                        id="googlesubscription"
                        name="subscription"
                        value="${mdata.subscription}"
                        placeholder="구독 여부 정보를 입력 하세요."
                        required="required"
                        min="0"
                        max="1"
                        readonly="readonly"
                />
            </div>

            <div class="mb-3 col-md-6">
                <label for="googlesignUpKind" class="form-label">가입 종류</label>
                <input
                        class="form-control"
                        type="text"
                        id="googlesignUpKind"
                        name="signUpKind"
                        value="${mdata.signUpKind}"
                        placeholder="${mdata.signUpKind}"
                        readonly="readonly"
                />
            </div>

        </div>

        <div class="mt-2">
            <button type="submit" class="btn btn-primary me-2">회원 정보 수정</button>
            <button type="reset" class="btn btn-outline-secondary">원래 대로</button>
        </div>

    </form>
</c:if>

<c:if test="${mdata.signUpKind eq '주주'}">
    <!-- Account -->
    <div class="card-body">
        <form id="updateMemberForm_JOOJOO" action="updateMember.do" method="POST">

            <div class="row">

                <div class="mb-3 col-md-6">
                    <label for="joojoomID" class="form-label">이메일</label>
                    <input
                            class="form-control"
                            type="text"
                            id="joojoomID"
                            name="mID"
                            value="${mdata.mID}"
                            placeholder="이메일을 입력 하세요."
                            required="required"
                            readonly="readonly"
                    />
                </div>

                <div class="mb-3 col-md-6">
                    <label for="joojoomPW" class="form-label">비밀번호</label>
                    <input
                            class="form-control"
                            type="text"
                            id="joojoomPW"
                            name="tmpMpw"
                            value="${mdata.mPW}"
                            placeholder="비밀번호를 입력 하세요."
                            required="required"
                    />
                </div>

                <div class="mb-3 col-md-6">
                    <label for="joojoomName" class="form-label">이름 (닉네임)</label>
                    <input
                            class="form-control"
                            type="text"
                            id="joojoomName"
                            name="tmpMname"
                            value="${mdata.mName}"
                            placeholder="이름 (닉네임) 을 입력 하세요."
                            required="required"
                    />
                </div>

                <div class="mb-3 col-md-6">
                    <label
                            class="subscription"
                            for="joojoophoneNumber">
                        구독 여부 [ 0 : 구독 X | 1 : 구독 O ]
                    </label>
                    <input
                            class="form-control"
                            type="number"
                            id="joojoosubscription"
                            name="subscription"
                            value="${mdata.subscription}"
                            placeholder="구독 여부 정보를 입력 하세요."
                            required="required"
                            min="0"
                            max="1"
                            readonly="readonly"
                    />
                </div>

                <div class="mb-3 col-md-6">
                    <label for="joojoophoneNumber" class="phoneNumber">전화번호</label>
                    <div class="input-group input-group-merge">
                        <span class="input-group-text">KO (+82)</span>
                        <input
                                class="form-control"
                                type="text"
                                id="joojoophoneNumber"
                                name="tmpMphone"
                                value="${mdata.mPhone}"
                                placeholder="${mdata.mPhone}"
                        />
                    </div>
                </div>

                <div class="mb-3 col-md-6">
                    <label for="joojoosignUpKind" class="form-label">가입 종류</label>
                    <input
                            class="form-control"
                            type="text"
                            id="joojoosignUpKind"
                            name="signUpKind"
                            value="${mdata.signUpKind}"
                            placeholder="${mdata.signUpKind}"
                            readonly="readonly"
                    />
                </div>

            </div>

            <div class="mt-2">
                <button type="submit" class="btn btn-primary me-2">회원 정보 수정</button>
                <button type="reset" class="btn btn-outline-secondary">원래 대로</button>
                <hr>
            </div>

        </form>

</c:if>