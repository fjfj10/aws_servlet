import axios from 'axios';
import React, { useState } from 'react';

function Signin(props) {
    const [ signinInput, setSigninInput ] = useState({
        username: "",
        password: ""
    });

    const handleInputChange = (e) => {
        setSigninInput({
            ...signinInput,
            [e.target.name]: e.target.value
        })
    }

    const handleSigninClick = async () => {
        try {
            // 조회여도 인증과 같은 일을 할때는 get이아닌 post를 사용한다 => get일때는 url에 내용이 나타나기 때문에 보안취약
            const response = await axios.post("http://localhost:8080/servlet_study_cheawon/auth/signin", signinInput);

            if (!response.data.token) {
                alert("로그인 실패");
                return;
            }
            localStorage.setItem("token", response.data?.token);
            
            alert("환영합니다.");
            
        } catch (error) {
            console.log(error);
        }
        
    };

    return (
        <>
            <h1>로그인</h1>
            <div><input type="text" name='username' onChange={handleInputChange} placeholder='username'/></div>
            <div><input type="password" name='password' onChange={handleInputChange} placeholder='password'/></div>
            <div><button onClick={handleSigninClick}>로그인</button></div>
        </>
    );
}

export default Signin;