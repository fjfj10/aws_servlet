import React from 'react';
/** @jsxImportSource @emotion/react */
import { Global, css } from '@emotion/react'
import MainLayout from "./components/MainLayout/MainLayout";
import { Route, Routes } from 'react-router-dom';
import Signup from './pages/Signup/Signup';
import Signin from './pages/Signin/Signin';
import Asynchronous from './pages/Asynchronous/Asynchronous';
import Mypage from './pages/Mypage/Mypage';
import EditProfile from './pages/EditProfile/EditProfile';

const SCommon = css`
  * {
    box-sizing: border-box;
  }
`;

function App() {
  return (
    <>
      <Global styles={SCommon} />
      <MainLayout>
        <Routes>
          <Route path='/' />
          <Route path='/signin' element={ <Signin /> } />
          <Route path='/signup' element={ <Signup /> } />
          <Route path='/async' element={ <Asynchronous /> } />
          <Route path='/mypage' element={ <Mypage /> } />
          <Route path='/mypage/edit' element={ <EditProfile /> } />
        </Routes>
      </MainLayout>
    </>
  );
}

export default App;