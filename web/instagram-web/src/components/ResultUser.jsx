import React from 'react';
export default function ResultOfSearch({results}) {

    // tiene que quedar en el mismo reglon
    return (
        <div >

            <div className="card-deck align-content-center card-columns pt-5" >
                {results && results.map(result => (
                    <div key={result.id} className="mx-auto" >
                        <a href={`/user/${result.id}`} >
                            <p >
                            <img className="border border-primary rounded-circle mr-2"
                                 style={{width: "80px", height: "80px"}} src={result.image} align="left"/>

                            {result.name}

                            </p>
                        </a>
                    </div>
                ))}
            </div>

        </div>
    );
}