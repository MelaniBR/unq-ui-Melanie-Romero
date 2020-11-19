export default function ResultPost({results}) {
    // tiene que quedar en el mismo reglon
    return (
        <div className="media-button grid-item">

            <div className="p-3 text-center">
                {results.map(result => (
                    <div key={result.id}>
                        <a href={`/post/${result.id}`}>
                            <img className="border border-primary rounded-circle d-inline mr-2"
                                 style={{width: "40px", height: "40px"}} src={result.portrait}/>

                        </a>
                    </div>
                ))}
            </div>
        </div>
     );
}