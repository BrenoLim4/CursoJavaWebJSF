/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util.SelectItemConverter;

import br.com.dao.Persistivel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

/**
 *
 * @author 99039833
 */
@FacesConverter(value = "EntityConverter")
public class EntityConverter implements Converter {

    private Persistivel item;

    @Override
    public Object getAsObject(FacesContext ctx, UIComponent comp, String value) {
        Object o = null;
        if (!(value == null || value.isEmpty())) {
            o = this.getSelectedItemAsEntity(comp, value);
        }
        return o;
    }

    @Override
    public String getAsString(FacesContext ctx, UIComponent comp, Object value) {
        String s = "";
        if (value != null && value instanceof Persistivel) {
            s = ((Persistivel) value).getIdentifier().toString();
        }
        return s;
    }

    private Persistivel getSelectedItemAsEntity(UIComponent comp, String value) {
        
        item = null;

        Long itemId = Long.valueOf(value);
        Predicate<Persistivel> predicate = i -> i.getIdentifier().equals(itemId);
        comp.getChildren().stream()
                .filter(uic -> uic instanceof UISelectItems)
                .forEach(uic -> {
                    setPersistivel(carregarList((UISelectItems) uic)
                            .stream()
                            .filter(predicate)
                            .findAny()
                            .orElse(null));
                });
        return item;
    }

    private List<Persistivel> carregarList(UISelectItems uic) {
        List<Persistivel> list = new ArrayList<>();
        ((List<SelectItem>) uic.getValue())
                .forEach(next -> list.add((Persistivel) next.getValue()));
        return list;
    }

    private void setPersistivel(Persistivel object) {
        this.item = object;
    }
}
