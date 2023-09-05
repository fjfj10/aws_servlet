import React, { useState } from 'react';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const SInputLayout = css`
    margin-bottom: 15px;
    width: 60%;
    height: 40px;

    & > input {
        width: 100%;
        height: 100%;
        text-align: center;
    }
`;

function Signup(props) {
    //Route이동시 사용(Link or navigate)
    const navigate = useNavigate();

    const [ signupUser, setSignupUser ] = useState({
        username: "",
        password: "",
        name: "",
        email: ""
    });

    const handleInputChange = async(e) => {
        setSignupUser({
            ...signupUser,
            [e.target.name]: e.target.value
        })
        //set함수는 비동기 함수 
        //await사용 불가 => promise가 아니라서
    }

    const handleSubmitClick = () => {
        //회원가입 요청
        const option = {
            params: {
                username: signupUser.username
            }
            
        }
        // callback -> promise -> async/await로 변경
        const signup = async () => {
            let response = await axios.get("http://localhost:8080/servlet_study_cheawon/auth/signup/duplicate/username", option);
            
            if (response.data) {
                alert("중복되는 아이디입니다.")
                return;
            } 
            
            // 회원가입 실패 시 오류 처리
            try{
                response = await axios.post("http://localhost:8080/servlet_study_cheawon/auth/signup", signupUser)
                if (!response.data) {
                    throw new Error(response);
                }
                alert("회원가입 성공!");
                navigate('/signin');
            }catch(error) {
                console.log(error);
            }
            return response;    
        };
    
        signup();

        // 위와 아래는 같다
        // axios.get("http://localhost:8080//servlet_study_cheawon/auth/signup/duplicate/username", option)
        // .then((response) => {
        //     //axios.post(servlet, 객체)하면 자동으로 Json변경을 해줌
        //     console.log(response);
        //     const isDuplicateUsername = response.data;

        //     if (isDuplicateUsername) {
        //         //아이디가 중복되었다.
        
        //     }else {
        //         //사용 가능한 아이디.
        
        //     }
        // }).catch((error) => {
        
        // })
    }

    return (
        <>
            <h1>회원가입</h1>
            <div css={SInputLayout}>
                <input type="text" name="username" placeholder="username" onChange={handleInputChange}/>
            </div>
            <div css={SInputLayout}>
                <input type="password" name="password" placeholder="password" onChange={handleInputChange}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name="name" placeholder="name" onChange={handleInputChange}/>
            </div>
            <div css={SInputLayout}>
                <input type="text" name="email" placeholder="email" onChange={handleInputChange}/>
            </div>
            <div>
                <button onClick={handleSubmitClick}>가입하기</button>
            </div>
        </>
    );
}

export default Signup;