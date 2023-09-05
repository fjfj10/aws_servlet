import React, { useEffect, useState } from 'react';
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
function EditProfile(props) {
    const [ profile, setProfile ] = useState();

    const navigate = useNavigate();

    useEffect(() => {
        const getProfile = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/servlet_study_cheawon/mypage/profile`, {
                    headers: {
                        Authorization: localStorage.getItem("token")
                    }
                });
                setProfile(response.data);
            } catch (error) {
                console.log(error);
            }
        }
        getProfile();
    }, []);

    const handleInputChange = (e) => {
        setProfile({
            ...profile,
            [e.target.name]: e.target.value
        });
    }

    const handleUpdateClick = () => {
        const submit = async () => {
            const option = {
                headers: {
                    Authorization: localStorage.getItem("token")
                }
            }
            const response = await axios.put("http://localhost:8080/servlet_study_cheawon/mypage/profile", profile, option);
            if (response.data) {
                alert("수정 완료!!");
                navigate('/mypage');
                return;
            }
        }

        submit();
    }

    return (
        <>
            <h1>회원정보 수정</h1>
            <div css={SInputLayout}>
                <input type="text" name="username" placeholder="username" onChange={handleInputChange} defaultValue={profile?.username} />
            </div>
            <div css={SInputLayout}>
                <input type="password" name="password" placeholder="password" onChange={handleInputChange} defaultValue={profile?.password} />
            </div>
            <div css={SInputLayout}>
                <input type="text" name="name" placeholder="name" onChange={handleInputChange} defaultValue={profile?.name} />
            </div>
            <div css={SInputLayout}>
                <input type="text" name="email" placeholder="email" onChange={handleInputChange} defaultValue={profile?.email} />
            </div>
            <div>
                <button onClick={handleUpdateClick}>수정하기</button>
            </div>
        </>
    );
}

export default EditProfile;