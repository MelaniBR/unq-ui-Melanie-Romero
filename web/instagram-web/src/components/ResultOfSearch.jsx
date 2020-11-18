import React from 'react';
import ResultUser from './ResultUser';
/// recibiria el resultado para el manejo del resultado
// solo de usario
export default function ResultOfSearch({results}) {
    if(!results){
        return(
            <h2>
                Sin Resultados
            </h2>
        );
    } else {
        return(
            <div className="container-fluid">
                <div className="row">
                    {results.map (result => (
                        <ResultUser content={result} />
                    ))}
                </div>
            </div>
        );
    }

}