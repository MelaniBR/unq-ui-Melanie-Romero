import React from 'react';
import { Link, NavLink } from 'react-router-dom';
export default function ResultOfSearch({results}) {

    // tiene que quedar en el mismo reglon
    return (
        <div className="my-3 p-3 bg-white rounded shadow-sm">
            <h6 className="border-bottom border-gray pb-2 mb-0">Search by user result</h6>
            {results && results.map(result => (

                <div className="media text-muted pt-3">
                    <img className="border border-primary rounded-circle mr-2"
                         style={{width: "60px", height: "60px"}} src={result.image} align="left"/>

                    <p className="media-body pt-3 pb-3 mb-0 big lh-125 border-bottom border-gray">
                        <strong className="d-block text-gray-dark"> <Link to={`/user/${result.id}`}>{result.name}</Link></strong>
                    </p>
                </div>
            ))}
        </div>
    );
}