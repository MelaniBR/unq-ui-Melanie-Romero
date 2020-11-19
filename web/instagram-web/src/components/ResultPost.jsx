import React from 'react';
export default function ResultPost({results}) {

    return (
        <div className="media-button grid-item">

            <div className="row posters">
                {results.map(result => (
                    <div key={result.id}>
                        <a href={`/post/${result.id}`}>
                            <img  className="media-poster-img"  src={result.portrait}/>

                        </a>
                    </div>
                ))}
            </div>
        </div>
     );
}