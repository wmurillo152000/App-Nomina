FROM node:20-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm ci
COPY . .
RUN npm run build --prod

FROM nginx:alpine
COPY --from=builder /app/dist/frontend/browser /usr/share/nginx/html
<<<<<<< HEAD
COPY nginx.conf /etc/nginx/conf.d/default.conf
=======
COPY frontend/nginx.conf /etc/nginx/conf.d/default.conf
>>>>>>> 7b9455b3d7abf18e21c0bf9377c72222cbc949a8
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]