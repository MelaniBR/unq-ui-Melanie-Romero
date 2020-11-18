import React from 'react';
import ResultUser from './ResultUser';

export default function ResultOfSearch({results}) {
    if(results){
        return(
            <div className="container-fluid">
                <div className="row">
                    {results.content.map(result => (
                        <div key={result.id} className="poster" >
                            <ResultUser content={result}></ResultUser>
                        </div>
                    ))}
                </div>
            </div>
        );
    } else {
        return(

            <h2>
                Sin Resultados
            </h2>

        );
    }

}