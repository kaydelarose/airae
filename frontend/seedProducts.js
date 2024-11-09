import dotenv from 'dotenv';
import { MongoClient } from 'mongodb';
import fs from 'fs';

dotenv.config();

const uri = process.env.MONGO_URI;
const client = new MongoClient(uri);

async function seedProducts() {
    try {
        await client.connect();
        const database = client.db('airae');
        const collection = database.collection('products');

        const rawData = fs.readFileSync('sunscreen.json');
        const products = JSON.parse(rawData);

        for (const product of products) {
            const exists = await collection.findOne({ name: product.name });
            if (!exists) {
                await collection.insertOne(product);
                console.log(`Inserted product: ${product.name}`);
            } else {
                console.log(`Skipped duplicate product: ${product.name}`);
            }
        }
        console.log("Seeding completed!");
    } catch (error) {
        console.error("Error seeding products:", error);
    } finally {
        await client.close();
    }
}

seedProducts();
