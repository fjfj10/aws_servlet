import React from 'react';
/** @jsxImportSource @emotion/react */
import { css } from '@emotion/react'
import MainHeader from '../MainHeader/MainHeader';

const SLayout = css`
    margin: 100px auto;
    border: 1px solid #dbdbdb;
    width: 600px;
    height: 600px;
`;

const SContainer = css`
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

function MainLayout({ children }) {
    return (
        <div css={SLayout}>
            <MainHeader />
            <div css={SContainer}>
                {children}
            </div>
        </div>
    );
}

export default MainLayout;