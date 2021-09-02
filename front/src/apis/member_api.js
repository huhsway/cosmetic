import ApiService from "@/apis/api";

function requestJoinMember(member){
    return ApiService.post('/api/v1/members/join', member);
}

function requestLoginMember(member) {
    const payload = {
        data: {
            email: member.email,
            password: member.password
        }
    }
    return ApiService.post('/api/v1/members/login', payload.data);
}

export {
    requestLoginMember,
    requestJoinMember,
}