import React, { useState,useEffect } from 'react';
import {search} from "./Api";
import ResultOfSearch from './ResultOfSearch';
import {useLocation} from 'react-router-dom';

function useQuery() {
    return new URLSearchParams(useLocation().search);
}

const Search =(props)=>{

    const [result, setResult] = useState();
    const query = useQuery();
    const busqueda = query.get("content");

    useEffect( () => {
        search(busqueda,props.auth.token).then(res => {
            setResult(res.data.content);
        })
    }, []);

    return (<div className="search-page">

            <h2>{busqueda} </h2>
        <div className="row">
            <ResultOfSearch results={result}></ResultOfSearch>

        </div>
        </div>
    );
}
    export default Search;