package realm.state;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class RealmStateFragmentAdapter extends TypeAdapter<IRealmStateFragment> {
    @Override
    public void write(JsonWriter jsonWriter, IRealmStateFragment realmStateFragment) throws IOException {
        jsonWriter.jsonValue(realmStateFragment.toJson());
    }

    @Override
    public IRealmStateFragment read(JsonReader jsonReader) throws IOException {
        throw new UnsupportedOperationException("Deserialization to IRealmStateFragment is not supported.");
    }
}
