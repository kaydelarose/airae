import { MongoClient } from 'mongodb';
import fs from 'fs';
import dotenv from 'dotenv';

dotenv.config();

const uri = process.env.MONGO_URI;
const client = new MongoClient(uri);

async function seedIngredients() {
    try {
        await client.connect();
        const database = client.db('airae');
        const collection = database.collection('ingredients');

        const rawData = fs.readFileSync('fragrance.json'); 
        const ingredients = JSON.parse(rawData);

        for (const ingredient of ingredients) {
            const exists = await collection.findOne({ name: ingredient.name });
            if (!exists) {
                await collection.insertOne(ingredient);
                console.log(`Inserted ingredient: ${ingredient.name}`);
            } else {
                console.log(`Skipped duplicate ingredient: ${ingredient.name}`);
            }
        }
        console.log("Seeding completed!");
    } catch (error) {
        console.error("Error seeding ingredients:", error);
    } finally {
        await client.close();
    }
}

seedIngredients();
