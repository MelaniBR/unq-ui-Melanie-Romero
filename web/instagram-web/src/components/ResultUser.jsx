import React from 'react';
export default function ResultOfSearch({results}) {

    // tiene que quedar en el mismo reglon
    return (
        <div className="media-button grid-item">

            <div  >
                {results && results.map(result => (
                    <div key={result.id}>
                        <a href={`/user/${result.id}`}>
                            <img className="border border-primary rounded-circle d-inline mr-2"
                                 style={{width: "50px", height: "50px"}} src={result.image}/>
                                 <h2> {result.id}</h2>

                        </a>
                    </div>
                ))}
            </div>

        </div>
    );
}