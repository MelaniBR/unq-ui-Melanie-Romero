import React, {useState, useEffect} from 'react';
import {search} from "./Api";
import ResultOfSearch from './ResultOfSearch';
import {useLocation} from 'react-router-dom';

export function useQuery() {
    return new URLSearchParams(useLocation().search);
}

const Search = (props) => {

    const [result, setResult] = useState([]);
    const query = useQuery();
    const searched = query.get("content");

    useEffect(() => {

        search(encodeURIComponent(searched), props.auth.token).then(res => {
            setResult(res.data.content);
        })
    }, [searched]);

    if (result) {
        return (
            <div >
                <h2>{searched} : </h2>
                <ResultOfSearch search={searched} results={result} ></ResultOfSearch>
            </div>);
    } else {
        return (
            <h2>
                Sin Resultados
            </h2>
        );
    }
};
export default Search;