import React from 'react';

function Asynchronous(props) {
    
    /* 
        동기(Synchronous): 순서대로 동작O
        비동기(Asynchronous): 순서대로 동작X
        
        콜백을 쓰는 이유: 비동기 처리안에서 내가 지정한 순서대로 함수 호출(동기처리)하려고

    */
    let num = 0;

    const handleClick = () => {
        num++;

        const click1 = (num) => {
            console.log(`${num} Click1!`);
        }
        const click1After = () => {
            console.log("Click1 후에 실행");
        }
        const click2 = (num) => {
            console.log(`${num} Click2!`);
        }
        const click2After = () => {
            console.log("Click2 후에 실행");
        }

        const clickFx = (fx1, fx2) => {
            fx1(num);
            fx2();
        }
        //                                          fx1       fx2
        setTimeout(clickFx, Math.random(3) * 1000, click1, click1After);    // 비동기
        click1After();  // 동기 *비동기에 시간을 0으로 줘도 동기가 더 빨리 일어난다 => 비동기 안에서 순서를 정한다(=콜백)
        setTimeout(clickFx, Math.random(3) * 1000, click2, click2After);    // 비동기
        click2After();  //동기

        // 비동기 함수인 setTimeout에서 click1다음에 fx(click1After)순서로 나오게 하고 싶으면 3번째 변수를 사용한다
        // setTimeout((fx) => {
        //     click1(num);
        //     fx();
        // }, Math.random(3) * 1000, click1After);

        // setTimeout((fx) => {
        //     click2(num);
        //     fx();
        // }, Math.random(3) * 1000, click2After);
    }

    const handleClick2 = () => {
        const p1 = new Promise((resolve, reject) => {
            const num = Math.random(4);
            if (Math.round(num % 2, 0) === 0) {
                resolve("짝수");
            }else {
                reject(new Error("홀수"));
            }
            // resolve("프로미스 실행!!"); //p1.then(result)의 result에 "프로미스 실행!!"을 넣어준다 
        });
        // promise는 비동기 그 안에서 then을 기용하여 순서를 정할 수 있다(동기처리) = 콜백과 유사 = 콜백대신 사용가능 => 콜백보다 가시성 좋다
        p1.then(result => {
            console.log(result);
            return "첫번째 then의 리턴";
        })
        .then(result => {
            console.log(result);
        })
        .catch(error => {
            console.log(error);
        });
    }
    
    return (
        <div>
            <button onClick={handleClick}>클릭</button>
            <button onClick={handleClick2}>클릭2</button>
        </div>
    );
}

export default Asynchronous;