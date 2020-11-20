import React from 'react';
export default function ResultPost({results}) {

    return (
        <div >

            <div className="card-deck">
                {results.map(result => (

                    <div key={result.id}  className="card" className="col-6 col-md-4 col-lg-3 mb-4">
                        <a href={`/post/${result.id}`} >
                            <img  className="card-img-top"  src={result.portrait}/>
                        </a>
                        </div>
                ))}
            </div>
        </div>
     );
}