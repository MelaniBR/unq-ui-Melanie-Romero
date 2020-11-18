import React, { useState } from 'react';
import {search} from "./Api";
import ResultOfSearch from './ResultOfSearch';


const Search = (props) => {

    const [data, setData] = useState();
    const [result, setResult] = useState();

    const useEffect=() => {
        search(data,props.auth.token).then(res => {
            setResult(res.data);
        })
        console.log(result)
    }

    return (<div className="search-page">

                <input className="form-control mr-sm-2"  placeholder="Search" aria-label="Search"
                       value={data}
                       onChange={event => setData(event.target.value)}  />
                <button className="btn btn-primary my-2 my-sm-0" type="submit" onClick={useEffect} >Search</button>
        <div className="row">
            <ResultOfSearch results={result}></ResultOfSearch>

        </div>
        </div>
    );
}

    export default Search;