import ResultUser from './ResultUser';
import ResultPost from "./ResultPost";

export default function ResultOfSearch({results, search}) {
    if (search.include('#')) {
        return <ResultPost content={results}/>;
    } else {
        return <ResultUser content={results}></ResultUser>;
    }
};

