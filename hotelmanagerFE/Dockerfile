FROM node:16-alpine

# Install Yarn
RUN apk add --no-cache yarn

COPY package*.json /app/hotelmanagerFE/

COPY . /app/hotelmanagerFE/

WORKDIR /app/hotelmanagerFE/

RUN yarn install

EXPOSE 3000

CMD ["yarn", "dev"]