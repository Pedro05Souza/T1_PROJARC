package com.example.demo.Infraestructure.Repositories;


/**
 * This class is used to encapsulate the result of a persisted entity and its
 * corresponding model.
 * 
 * The purpose of this class is to provide a way to return both
 * the model and the entity from a repository method, in case
 * an external resource needs both the model and the entity.
 * 
 * @param <TModel>  The type of the model.
 * @param <TEntity> The type of the entity.
 * 
 */
public class PersistedResult<TModel, TEntity> {
    private final TModel model;
    private final TEntity entity;

    public PersistedResult(TModel model, TEntity entity) {
        this.model = model;
        this.entity = entity;
    }

    public TModel getModel() {
        return model;
    }

    public TEntity getEntity() {
        return entity;
    }

}
