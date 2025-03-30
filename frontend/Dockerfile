# Estágio de build (gera os arquivos estáticos)
FROM node:22 AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build  # Gera os arquivos em /app/build
EXPOSE 3000
CMD ["npm", "start"]