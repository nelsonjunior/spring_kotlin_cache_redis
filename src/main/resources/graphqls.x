schema {
   query: Query
}

type Anuncio {
  id: ID!
  titulo: String!
  resumo: String!
  comments: [Comment]!
}

type Comment {
  id: ID!
  text: String!
  usuario: String!
  data: Date!
}

type Query {
  obterAnuncios: [Anuncio]!
  contarAnuncios: Long!
  obterAnuncioPorId(id: Long) : Anuncio!
}

type Mutation {
    novoAnuncio(titulo: String!, resumo: Int!) : Anuncio
}