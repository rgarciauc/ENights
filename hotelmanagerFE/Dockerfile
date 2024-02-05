FROM node:16-alpine

# Install Yarn
RUN apk add --no-cache yarn

WORKDIR /app/hotelmanagerFE

COPY package*.json /app/hotelmanagerFE/

RUN yarn install

COPY . /app/hotelmanagerFE/

EXPOSE 3000

CMD ["yarn", "dev"]