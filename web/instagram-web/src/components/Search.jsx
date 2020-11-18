import React, { useState } from 'react';
import {search} from "./Api";
import ResultOfSearch from './ResultOfSearch';


const Search = (props) => {

    const [content, setContent] = useState([]);
    const [result, setResult] = useState([]);

    const useEffect=() => {
        search(content,props.auth.token).then(res => {
            setResult(res.data);
        })
    }

    return (<div className="search-page">

                <input className="form-control mr-sm-2"  placeholder="Search" aria-label="Search"
                       value={content}
                       onChange={event => setContent(event.target.value)}  />
                <button className="btn btn-primary my-2 my-sm-0" type="submit" onClick={useEffect} >Search</button>



            <h2>

                <h2>
                    Resultado de la Búsqueda : {content}
                </h2>
                <ResultOfSearch results={result} />

            </h2>
        </div>
    );
}

    export default Search;