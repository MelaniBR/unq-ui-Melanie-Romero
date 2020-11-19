import ResultUser from './ResultUser';
import ResultPost from "./ResultPost";

export default function ResultOfSearch({results, search}) {
    if (search.charAt(0)==('#')) {
        return <ResultPost results={results}/>;
    } else {
        return <ResultUser results={results}></ResultUser>;
    }
};

