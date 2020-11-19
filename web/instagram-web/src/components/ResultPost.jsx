import React from 'react';
export default function ResultPost({results}) {

    return (
        <div >

            <div className="card-deck">
                {results.map(result => (

                    <div key={result.id}  className="card" style={{width:"100px", height:"100px"}}>
                        <a href={`/post/${result.id}`} >
                            <img  className="card-img-top"  src={result.portrait}/>
                        </a>
                        </div>
                ))}
            </div>
        </div>
     );
}