const ClientList = ({clients}) => {
    console.log(clients);
    return (
        <ul>
            <li>
                {clients.map(client => client.name)}
            </li>
        </ul>
    );
}
export default ClientList