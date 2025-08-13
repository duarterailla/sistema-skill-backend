-- Criação das sequences
CREATE SEQUENCE seq_usuario_id START 1;
CREATE SEQUENCE seq_skill_id START 1;
CREATE SEQUENCE seq_user_skill_id START 1;
CREATE SEQUENCE seq_categoria_id START 1;

-- Tabela de usuários
CREATE TABLE usuario (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_usuario_id'),
    login VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    nickname VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    status VARCHAR(20),
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ultimo_acesso TIMESTAMP
);

-- Tabela de categorias
CREATE TABLE categorias (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_categoria_id'),
    nome VARCHAR(100) NOT NULL UNIQUE
);

-- Tabela de skills
CREATE TABLE skills (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_skill_id'),
    nome VARCHAR(100) NOT NULL UNIQUE,
    descricao TEXT,
    imagem_url TEXT,
    categoria_id BIGINT REFERENCES categorias(id)
);

-- Tabela associativa entre usuário e skill
CREATE TABLE user_skills (
    id BIGINT PRIMARY KEY DEFAULT nextval('seq_user_skill_id'),
    user_id BIGINT NOT NULL,
    skill_id BIGINT NOT NULL,
    level VARCHAR(20) NOT NULL,
    descricao VARCHAR(255),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES usuario(id) ON DELETE CASCADE,
    CONSTRAINT fk_skill FOREIGN KEY (skill_id) REFERENCES skills(id) ON DELETE CASCADE,
    CONSTRAINT unique_user_skill UNIQUE (user_id, skill_id)
);

-- Insere categorias
INSERT INTO categorias (nome) VALUES
('Linguagens de Programação'),
('Desenvolvimento Frontend'),
('Desenvolvimento Backend'),
('Bancos de Dados'),
('DevOps, Cloud & Infraestrutura'),
('Desenvolvimento Mobile'),
('Dados, Machine Learning & IA'),
('Metodologias & Ferramentas');

-- Criação da tabela de categorias
CREATE TABLE IF NOT EXISTS categorias (
  id SERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL UNIQUE
);

-- Adiciona coluna de categoria na tabela skills
ALTER TABLE skills ADD COLUMN IF NOT EXISTS categoria_id INTEGER REFERENCES categorias(id);

-- Insere categorias
INSERT INTO categorias (nome) VALUES
('Linguagens de Programação'),
('Desenvolvimento Frontend'),
('Desenvolvimento Backend'),
('Bancos de Dados'),
('DevOps, Cloud & Infraestrutura'),
('Desenvolvimento Mobile'),
('Dados, Machine Learning & IA'),
('Metodologias & Ferramentas');

-- Insere skills por categoria
-- Linguagens de Programação (categoria_id = 1)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('JavaScript', 'Linguagem de programação essencial para a web, executada no cliente e no servidor (Node.js).', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/javascript/javascript-original.svg', 1),
('TypeScript', 'Superset do JavaScript que adiciona tipagem estática, aumentando a robustez do código.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/typescript/typescript-original.svg', 1),
('Python', 'Linguagem versátil, popular para ciência de dados, IA, desenvolvimento web e automação.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/python/python-original.svg', 1),
('Java', 'Linguagem robusta, orientada a objetos, amplamente usada para aplicações corporativas e Android.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg', 1),
('C#', 'Linguagem da Microsoft para desenvolvimento de aplicações Windows, web (ASP.NET) e jogos (Unity).', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/csharp/csharp-original.svg', 1),
('Go', 'Linguagem compilada do Google, focada em simplicidade, concorrência e performance.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/go/go-original.svg', 1),
('Rust', 'Linguagem focada em segurança de memória e performance, ideal para sistemas de baixo nível.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/rust/rust-plain.svg', 1),
('PHP', 'Linguagem de script popular para desenvolvimento web, base de sistemas como WordPress e Laravel.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/php/php-original.svg', 1),
('Ruby', 'Linguagem de script conhecida pela sua sintaxe elegante e pelo framework Ruby on Rails.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/ruby/ruby-original.svg', 1),
('Kotlin', 'Linguagem moderna da JetBrains, oficial para desenvolvimento Android e crescente no backend.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kotlin/kotlin-original.svg', 1),
('Swift', 'Linguagem da Apple para desenvolvimento de aplicativos para iOS, macOS, watchOS e tvOS.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/swift/swift-original.svg', 1),
('C++', 'Linguagem poderosa e de alta performance, usada em jogos, sistemas embarcados e software de sistema.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/cplusplus/cplusplus-original.svg', 1),
('C', 'Linguagem fundamental e de baixo nível, base para muitos sistemas operacionais e outras linguagens.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/c/c-original.svg', 1),
('Scala', 'Linguagem que combina programação orientada a objetos e funcional, executada na JVM.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/scala/scala-original.svg', 1),
('Dart', 'Linguagem do Google otimizada para UI, usada principalmente com o framework Flutter.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/dart/dart-original.svg', 1),
('Bash', 'Shell e linguagem de script padrão em sistemas Unix-like, essencial para automação e DevOps.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bash/bash-original.svg', 1);

-- Desenvolvimento Frontend (categoria_id = 2)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('HTML5', 'Linguagem de marcação padrão para criar e estruturar conteúdo em páginas web.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/html5/html5-original.svg', 2),
('CSS3', 'Linguagem de estilização para descrever a apresentação visual de documentos web.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/css3/css3-original.svg', 2),
('React', 'Biblioteca JavaScript do Facebook para construir interfaces de usuário componentizadas.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg', 2),
('Angular', 'Framework front-end do Google para criar aplicações web complexas e de página única (SPA).', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/angularjs/angularjs-original.svg', 2),
('Vue.js', 'Framework JavaScript progressivo para construir interfaces de usuário, conhecido por sua simplicidade.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vuejs/vuejs-original.svg', 2),
('Svelte', 'Framework que compila o código para JavaScript vanilla, resultando em aplicações mais rápidas.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/svelte/svelte-original.svg', 2),
('jQuery', 'Biblioteca JavaScript clássica que simplifica a manipulação de DOM, eventos e AJAX.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jquery/jquery-original.svg', 2),
('Bootstrap', 'Framework CSS popular para desenvolver sites responsivos e mobile-first rapidamente.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/bootstrap/bootstrap-original.svg', 2),
('Tailwind CSS', 'Framework CSS utility-first para criar designs customizados sem sair do HTML.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/tailwindcss/tailwindcss-plain.svg', 2),
('SASS/SCSS', 'Pré-processador CSS que adiciona recursos como variáveis, aninhamento e mixins.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/sass/sass-original.svg', 2),
('Redux', 'Biblioteca para gerenciamento de estado global em aplicações JavaScript, comumente usada com React.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redux/redux-original.svg', 2),
('Webpack', 'Empacotador de módulos para aplicações JavaScript modernas, otimizando e processando assets.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/webpack/webpack-original.svg', 2),
('Vite', 'Ferramenta de build moderna que oferece um desenvolvimento extremamente rápido com Hot Module Replacement.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/vitejs/vitejs-original.svg', 2),
('Jest', 'Framework de testes de JavaScript com foco em simplicidade, muito usado para testar aplicações React.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jest/jest-plain.svg', 2),
('Cypress', 'Ferramenta de testes end-to-end que permite testar tudo que roda em um navegador.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/cypressio/cypressio-original.svg', 2);

-- Desenvolvimento Backend (categoria_id = 3)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('Node.js', 'Ambiente de execução JavaScript no servidor, permitindo a criação de APIs e serviços back-end.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nodejs/nodejs-original.svg', 3),
('Express.js', 'Framework web minimalista e flexível para Node.js, padrão de fato para construção de APIs.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/express/express-original.svg', 3),
('NestJS', 'Framework Node.js progressivo para construir aplicações de servidor eficientes e escaláveis com TypeScript.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nestjs/nestjs-plain.svg', 3),
('Django', 'Framework Python de alto nível que encoraja o desenvolvimento rápido e o design limpo.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/django/django-plain.svg', 3),
('Flask', 'Micro-framework Python para desenvolvimento web, conhecido por sua simplicidade e flexibilidade.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/flask/flask-original.svg', 3),
('FastAPI', 'Framework web moderno e de alta performance para Python, baseado em tipos e com documentação automática.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/fastapi/fastapi-original.svg', 3),
('Spring', 'Framework abrangente para o ecossistema Java, usado para criar aplicações robustas e de alta performance.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original.svg', 3),
('.NET', 'Plataforma de desenvolvimento da Microsoft para criar aplicações web, desktop e mobile com C#.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/dot-net/dot-net-original.svg', 3),
('Laravel', 'Framework PHP com sintaxe expressiva e elegante para desenvolvimento web.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/laravel/laravel-plain.svg', 3),
('Ruby on Rails', 'Framework web em Ruby que otimiza a produtividade do desenvolvedor através de convenções.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/rails/rails-original-wordmark.svg', 3),
('REST API', 'Padrão de arquitetura para projetar APIs, usando métodos HTTP para comunicação.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/figma/figma-original.svg', 3),
('GraphQL', 'Linguagem de consulta para APIs que permite aos clientes solicitar exatamente os dados de que precisam.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/graphql/graphql-plain.svg', 3);

-- Bancos de Dados (categoria_id = 4)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('PostgreSQL', 'Sistema de banco de dados relacional de código aberto, conhecido por sua robustez e extensibilidade.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/postgresql/postgresql-original.svg', 4),
('MySQL', 'Sistema de banco de dados relacional de código aberto mais popular do mundo, base de muitas aplicações web.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mysql/mysql-original.svg', 4),
('MariaDB', 'Fork do MySQL criado pela comunidade, com foco em manter-se livre e de código aberto.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mariadb/mariadb-original.svg', 4),
('Microsoft SQL Server', 'Sistema de gerenciamento de banco de dados da Microsoft para ambientes corporativos.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/microsoftsqlserver/microsoftsqlserver-plain.svg', 4),
('Oracle Database', 'Banco de dados relacional líder de mercado para aplicações de grande escala e missão crítica.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/oracle/oracle-original.svg', 4),
('SQLite', 'Banco de dados embutido em uma biblioteca C, leve e ideal para aplicações mobile e desktop.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/sqlite/sqlite-original.svg', 4),
('MongoDB', 'Banco de dados NoSQL orientado a documentos, popular por sua flexibilidade e escalabilidade.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/mongodb/mongodb-original.svg', 4),
('Redis', 'Banco de dados em memória, usado como cache, message broker e armazenamento de estruturas de dados.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/redis/redis-original.svg', 4),
('Elasticsearch', 'Motor de busca e análise distribuído, usado para buscas de texto completo e análise de logs.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/elasticsearch/elasticsearch-original.svg', 4),
('DynamoDB', 'Banco de dados NoSQL chave-valor e de documentos da AWS, totalmente gerenciado e de alta performance.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/dynamodb/dynamodb-original.svg', 4),
('Firebase', 'Plataforma do Google que oferece um conjunto de ferramentas, incluindo bancos de dados (Firestore, Realtime DB).', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/firebase/firebase-plain.svg', 4);

-- DevOps, Cloud & Infraestrutura (categoria_id = 5)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('Docker', 'Plataforma para desenvolver, implantar e executar aplicações em contêineres isolados.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/docker/docker-original.svg', 5),
('Kubernetes', 'Sistema de orquestração de contêineres para automatizar a implantação, escalonamento e gerenciamento.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/kubernetes/kubernetes-plain.svg', 5),
('AWS (Amazon Web Services)', 'Plataforma de nuvem líder de mercado, oferecendo uma vasta gama de serviços.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/amazonwebservices/amazonwebservices-original.svg', 5),
('Microsoft Azure', 'Plataforma de nuvem da Microsoft, com forte integração com o ecossistema de produtos da empresa.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/azure/azure-original.svg', 5),
('Google Cloud Platform (GCP)', 'Plataforma de nuvem do Google, conhecida por sua expertise em dados, IA e Kubernetes.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/googlecloud/googlecloud-original.svg', 5),
('Terraform', 'Ferramenta de Infraestrutura como Código (IaC) para provisionar e gerenciar recursos em nuvem.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/terraform/terraform-original.svg', 5),
('Ansible', 'Ferramenta de automação de TI para provisionamento de software, gerenciamento de configuração e implantação.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/ansible/ansible-original.svg', 5),
('Jenkins', 'Servidor de automação de código aberto para configurar pipelines de Integração Contínua (CI/CD).', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jenkins/jenkins-original.svg', 5),
('GitLab', 'Plataforma DevOps completa, que inclui desde o controle de versão com Git até CI/CD e monitoramento.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gitlab/gitlab-original.svg', 5),
('GitHub Actions', 'Plataforma de CI/CD integrada ao GitHub para automatizar workflows de software.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/github/github-original.svg', 5),
('Prometheus', 'Ferramenta de monitoramento e alerta de código aberto, padrão no ecossistema de Kubernetes.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/prometheus/prometheus-original.svg', 5),
('Grafana', 'Plataforma de visualização e análise de métricas, permitindo criar dashboards interativos.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/grafana/grafana-original.svg', 5),
('Nginx', 'Servidor web de alta performance, também usado como proxy reverso, balanceador de carga e cache.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/nginx/nginx-original.svg', 5);

-- Desenvolvimento Mobile (categoria_id = 6)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('Flutter', 'Toolkit de UI do Google para criar aplicações compiladas nativamente para mobile, web e desktop.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/flutter/flutter-original.svg', 6),
('React Native', 'Framework para criar aplicações mobile nativas usando React e JavaScript.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/react/react-original.svg', 6),
('Android', 'Desenvolvimento nativo para o sistema operacional Android, primariamente com Kotlin ou Java.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/android/android-original.svg', 6),
('iOS', 'Desenvolvimento nativo para os sistemas da Apple (iPhone, iPad), usando Swift ou Objective-C.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apple/apple-original.svg', 6);

-- Dados, Machine Learning & IA (categoria_id = 7)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('Pandas', 'Biblioteca Python essencial para manipulação e análise de dados estruturados.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/pandas/pandas-original.svg', 7),
('NumPy', 'Biblioteca fundamental para computação científica em Python, oferecendo suporte a arrays e matrizes.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/numpy/numpy-original.svg', 7),
('Scikit-learn', 'Biblioteca Python com ferramentas simples e eficientes para mineração e análise de dados.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/scikitlearn/scikitlearn-original.svg', 7),
('TensorFlow', 'Plataforma de código aberto do Google para Machine Learning e redes neurais.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/tensorflow/tensorflow-original.svg', 7),
('PyTorch', 'Framework de Machine Learning de código aberto, conhecido por sua flexibilidade e velocidade.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/pytorch/pytorch-original.svg', 7),
('Jupyter', 'Ferramenta que permite criar e compartilhar documentos com código, visualizações e texto (notebooks).', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jupyter/jupyter-original.svg', 7),
('Apache Spark', 'Motor de processamento de dados em larga escala, unificado para big data e machine learning.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/apache/apache-original.svg', 7);

-- Metodologias & Ferramentas (categoria_id = 8)
INSERT INTO skills (nome, descricao, imagem_url, categoria_id) VALUES
('Git', 'Sistema de controle de versão distribuído, essencial para o desenvolvimento de software moderno.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/git/git-original.svg', 8),
('Agile', 'Conjunto de práticas para o desenvolvimento de software focado em entregas incrementais e colaboração.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jira/jira-original.svg', 8),
('Scrum', 'Framework ágil para gerenciar o desenvolvimento de produtos complexos, baseado em ciclos (sprints).', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jira/jira-original.svg', 8),
('Jira', 'Ferramenta popular para gerenciamento de projetos, rastreamento de bugs e desenvolvimento ágil.', 'https://cdn.jsdelivr.net/gh/devicons/devicon/icons/jira/jira-original.svg', 8);

