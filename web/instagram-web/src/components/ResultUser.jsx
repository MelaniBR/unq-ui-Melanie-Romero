export default function ResultOfSearch({content}){
    // tiene que quedar en el mismo reglon
    return (
        <div className="media-button grid-item">
            <h2> Resultado de Busqueda </h2>
            <div className="p-3 text-center">
                <a href={`/user/${content.id}`}>
                    <img className="border border-primary rounded-circle d-inline mr-2" style={{width:"40px", height:"40px"}} src={content.image} />
                    <h2> {content.name}</h2>
                </a>
            </div>
        </div>
    );
}